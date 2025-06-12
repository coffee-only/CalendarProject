#! usr/bin/nu

let headers = {
  "Content-Type": "application/json",
}


let payload = {
  username: "testing"
  email: "testing@gmail.com"
  password: "testing123"

}
let response = (
  try {
    http post http://localhost:8080/user/register ($payload | to json) --headers $headers
  } catch {|err|
    print $"POST FAILED: ($err)"
    $err
  }
)

print $response

