package model

import (
	"context"
	"go.mongodb.org/mongo-driver/bson"
	"killDDL/config"
	"killDDL/utils"
	"log"
)

type User struct {
	// username must be unique
	Username string `json:"username" bson:"username" form:"username" query:"username"`
	Password string `json:"password" bson:"password" form:"password" query:"password"`
}

func AddUser(user User) bool {

	// it means re-register
	if _, success := FindUserByUsername(user.Username); success {
		return false
	}

	user.Password = utils.SecurityEncrypt(user.Password)

	_, err := config.UserCol.InsertOne(context.TODO(), user)
	if err != nil {
		log.Println(err)
		return false
	}

	return true
}

func VerifyUser(user User) bool {
	dbUser, success := FindUserByUsername(user.Username);
	if !success{
		return false
	}

	if dbUser.Password != utils.SecurityEncrypt(user.Password) {
		return false
	}

	return true
}

func FindUserByUsername(username string) (User, bool) {

	var user User
	err := config.UserCol.FindOne(context.TODO(), bson.M{"username": username}).Decode(&user)
	if err != nil {
		return user, false
	}

	return user, true
}
