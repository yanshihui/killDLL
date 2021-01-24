package utils

import (
	"crypto/sha256"
	"encoding/base64"
)

func SecurityEncrypt(item string) string {
	byteStream := sha256.Sum256([]byte(item))
	return base64.StdEncoding.EncodeToString(byteStream[:])
}

