#! usr/bin/nu


let base_url = "http://localhost:8080"
let userpsw = $"Basic ("admin:admin123" | encode base64)"

let headers = [
  "Content-Type" "application/json"
  "Authorization" $userpsw
]


let payload = {
  "username": "testing"
  "email": "testing@gmail.com"
  "password": "testing123"

}
let response = (
  try {
    http post http://localhost:8080/user/register ($payload | to json) --headers $headers
  } catch {|err|
    print $"POST failed: ($err)"
    $err
  }
)

print $response

