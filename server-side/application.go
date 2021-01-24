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
		user model.User
	)

	if err := context.Bind(&user); err != nil{
		receive.Message = "post body error. can't bind"
		return context.JSON(http.StatusBadRequest, receive)
	}

	if _, success := model.FindUserByUsername(user.Username); !success{
		receive.Message = "re-register"
		return context.JSON(http.StatusBadRequest, receive)
	}

	if success := model.AddUser(user); !success{
		receive.Message = "insert error"
		return context.JSON(http.StatusInternalServerError, receive)
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

func taskQueryController(context echo.Context) error {
	return nil
}
