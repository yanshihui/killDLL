package config

import (
	"context"
	"encoding/json"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"io/ioutil"
	"log"
	"os"
	"time"
)

type MongoConfig struct {
	DbAddress      string `json:"db_address"`
	DBName         string `json:"db_name"`
	TimeOut        int    `json:"time_out"`
	TaskCollection string `json:"task_collection"`
	UserCollection string `json:"user_collection"`
}

type JwtConfig struct {
	SignKey string `json:"sign_key"`
	ExpTime int64  `json:"exp_time"`
}

type ConfigObj struct {
	MongoConfig           MongoConfig `json:"mongo_config"`
	JwtConfig             JwtConfig   `json:"jwt_config"`
	DefaultQueryTimeLimit int64       `json:"default_query_time_limit"`
}

var Config ConfigObj
var UserCol *mongo.Collection
var TaskCol *mongo.Collection

func init() {
	// load config from config file
	file, err := os.Open("config/config.json")
	if err != nil {
		log.Fatal(err)
	}

	byteStream, err := ioutil.ReadAll(file)
	if err != nil {
		log.Fatal(err)
	}

	if err = json.Unmarshal(byteStream, &Config); err != nil {
		log.Fatal(err)
	}

	// convert time unit
	Config.DefaultQueryTimeLimit *= int64(time.Hour) * 24
	Config.JwtConfig.ExpTime *= int64(time.Hour)


	// connect to database
	client, err := mongo.NewClient(options.Client().ApplyURI(Config.MongoConfig.DbAddress))
	if err != nil {
		log.Fatal(err)
	}

	ctx, cancel := context.WithTimeout(context.TODO(), time.Second*time.Duration(Config.MongoConfig.TimeOut))
	defer cancel()

	err = client.Connect(ctx)
	if err != nil {
		log.Fatal(err)
	}

	killddlDb := client.Database(Config.MongoConfig.DBName)
	TaskCol = killddlDb.Collection(Config.MongoConfig.TaskCollection)
	UserCol = killddlDb.Collection(Config.MongoConfig.UserCollection)
}
