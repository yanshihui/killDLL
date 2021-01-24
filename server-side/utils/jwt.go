package utils

import (
	"github.com/dgrijalva/jwt-go"
	"github.com/labstack/echo"
	"github.com/labstack/echo/middleware"
	"killDDL/config"
	"log"
	"time"
)

type JwtClaim struct {
	Username string `json:"username"`
	jwt.StandardClaims
}

func InitJwtConfig() middleware.JWTConfig {
	return middleware.JWTConfig{
		Claims: &JwtClaim{},
		SigningKey: config.Config.JwtConfig.SignKey,
		ContextKey: "user",

	}
}
func CreateToken(username string) (string, bool) {

	claims := &JwtClaim{
		Username: username,
		StandardClaims: jwt.StandardClaims{
			ExpiresAt: time.Now().Add(time.Duration(config.Config.JwtConfig.ExpTime)).Unix(),
		},
	}

	tokenTmp := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
	token, err := tokenTmp.SignedString([]byte(config.Config.JwtConfig.SignKey))
	if err != nil{
		log.Println("create token failure. ", err)
		return "", false
	}

	return token, true
}

func GetClaimsFromToken(context echo.Context) JwtClaim {

	user := context.Get("user").(*jwt.Token)
	claims := user.Claims.(*JwtClaim)
	return *claims
}