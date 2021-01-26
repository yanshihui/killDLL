package main

import (
	"github.com/labstack/echo"
	"github.com/labstack/echo/middleware"
	"killDDL/model"
	"killDDL/utils"
	"net/http"
)

func main() {

	e := echo.New()

	jwtConfig := utils.InitJwtConfig()

	e.POST("/login", loginController)

	e.POST("/register", registerController)

	e.GET("/task", taskQueryController, middleware.JWTWithConfig(jwtConfig))

	e.POST("/task", addTaskController, middleware.JWTWithConfig(jwtConfig))

	e.DELETE("/task", deleteTaskController, middleware.JWTWithConfig(jwtConfig))

	e.Logger.Fatal(e.Start(":4567"))
}

func loginController(context echo.Context) error {

	var (
		receive model.Receive
		user model.User
	)

	if err := context.Bind(&user); err != nil{
		receive.Message = "post body error. can't bind"
		return context.JSON(http.StatusBadRequest, receive)
	}

	// authorize user
	if success := model.VerifyUser(user); !success{
		receive.Message = "wrong username or password"
		return context.JSON(http.StatusUnauthorized, receive)
	}

	token, success := utils.CreateToken(user.Username)
	if !success{
		receive.Message = "generate token failure. please retry"
		return context.JSON(http.StatusInternalServerError, receive)
	}

	receive.Message = "success"
	receive.Token = token
	receive.Tasks = nil
	return context.JSON(http.StatusOK, receive)

}

func registerController(context echo.Context) error {

	var (
		receive model.Receive
		user    model.User
	)

	if err := context.Bind(&user); err != nil {
		receive.Message = "post body error. can't bind"
		return context.JSON(http.StatusBadRequest, receive)
	}

	if _, success := model.FindUserByUsername(user.Username); !success {
		receive.Message = "re-register"
		return context.JSON(http.StatusBadRequest, receive)
	}

	if success := model.AddUser(user); !success {
		receive.Message = "insert error"
		return context.JSON(http.StatusInternalServerError, receive)
	}

	token, success := utils.CreateToken(user.Username)
	if !success {
		receive.Message = "generate token failure. please retry"
		return context.JSON(http.StatusInternalServerError, receive)
	}

	receive.Message = "success"
	receive.Token = token
	receive.Tasks = nil
	return context.JSON(http.StatusOK, receive)

}

func taskQueryController(context echo.Context) error {
	return nil
}

func addTaskController(context echo.Context) error {

	var (
		task    model.Task
		receive model.Receive
	)

	if err := context.Bind(&task); err != nil {
		receive.Message = "post body error. can't bind"
		return context.JSON(http.StatusBadRequest, receive)
	}

	if success := model.AddTask(task); !success {
		receive.Message = "add task into db error"
		return context.JSON(http.StatusInternalServerError, receive)
	}

	receive.Message = "success"
	return context.JSON(http.StatusOK, receive)
}

func deleteTaskController(context echo.Context) error {

	var (
		deleteModel model.TaskDeleteModel
		receive     model.Receive
	)

	if err := context.Bind(&deleteModel); err != nil {
		receive.Message = "post body error. can't bind"
		return context.JSON(http.StatusBadRequest, receive)
	}

	if success := model.DeleteTasksByIds(deleteModel.TaskIds); !success {
		receive.Message = "delete error"
		return context.JSON(http.StatusInternalServerError, receive)
	}

	receive.Message = "success"
	return context.JSON(http.StatusOK, receive)
}
