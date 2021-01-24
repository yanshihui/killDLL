package model

import (
	"context"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo/options"
	"killDDL/config"
	"log"
	"time"
)

type TaskState string

const (
	Finalize TaskState = "Finalize"
	Draft    TaskState = "Draft"
)

type Task struct {
	Id        string            `json:"id" bson:"id"`
	StartTime int64             `json:"startTime" bson:"startTime"`
	EndTime   int64             `json:"endTime" bson:"endTime"`
	Theme     string            `json:"theme" bson:"theme"`
	SubTasks  []map[string]bool `json:"subTasks" bson:"subTasks"`
	Remarks   []string          `json:"remarks" bson:"remarks"`
	TaskState TaskState         `json:"taskState" bson:"taskState"`
}

type Receive struct {
	Message string `json:"message"`
	Token string `json:"token"`
	Tasks []Task `json:"tasks"`
}

func AddTask(task Task) bool {

	_, err := config.TaskCol.InsertOne(context.TODO(), task)
	if err != nil {
		log.Println(err)
		return false
	}

	return true
}

func DeleteTaskById(taskId string) bool {

	_, err := config.TaskCol.DeleteOne(context.TODO(), bson.M{"id": taskId})
	if err != nil {
		log.Println(err)
		return false
	}

	return true
}

func FindByTaskState(state TaskState) ([]Task, bool) {

	filter := bson.M{
		"taskState": state,
		"endTime":   bson.M{"$ge": time.Now().Unix() - config.Config.DefaultQueryTimeLimit},
	}

	opts := options.Find().SetSort(bson.M{"endTime": -1})
	cursor, err := config.TaskCol.Find(context.TODO(), filter, opts)
	if err != nil {
		log.Println(err)
		return nil, false
	}

	var tasks []Task
	err = cursor.All(context.TODO(), &tasks)
	if err != nil {
		log.Println(err)
		return nil, false
	}

	return tasks, true
}

func FindTasks(filter bson.M, options *options.FindOptions) ([]Task, bool) {

	cursor, err := config.TaskCol.Find(context.TODO(), filter, options)
	if err != nil {
		return nil, false
	}

	var tasks []Task
	err = cursor.All(context.TODO(), &tasks)
	if err != nil {
		return nil, false
	}

	return tasks, true
}
