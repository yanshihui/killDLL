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

type ContentType string

const (
	Finalize   TaskState = "Finalize"
	InProgress TaskState = "InProgress"
	Draft      TaskState = "Draft"

	TypeText  ContentType = "Text"
	TypeImage ContentType = "Image"
)

type SubTask struct {
	Content string `json:"content" bson:"content"`
	Done    bool   `json:"done" bson:"done"`
}

type ContentNode struct {
	Type    ContentType `json:"type" bson:"type"`
	Content string      `json:"content" bson:"content"`
}

type Task struct {
	Id                 string        `json:"id" bson:"id"`
	StartTime          int64         `json:"startTime" bson:"startTime"`
	EndTime            int64         `json:"endTime" bson:"endTime"`
	Theme              string        `json:"theme" bson:"theme"`
	SubTasks           []SubTask     `json:"subTasks" bson:"subTasks"`
	Remarks            []ContentNode `json:"remarks" bson:"remarks"`
	TaskState          TaskState     `json:"taskState" bson:"taskState"`
	DailyReminderTime  int           `json:"dailyReminderTime" bson:"dailyReminderTime"`
	RemainderMotto     string        `json:"remainderMotto" bson:"remainderMotto"`
	ScheduleAllocation []float64     `json:"scheduleAllocation" bson:"scheduleAllocation"`
}

type Receive struct {
	Message string `json:"message"`
	Token   string `json:"token"`
	Tasks   []Task `json:"tasks"`
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
