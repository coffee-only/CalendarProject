#! usr/bin/nu

let config = open env/env.toml

let url = $config.api.base_url
let basic_auth = $config.api.base_auth

let b = $"Basic ($basic_auth | encode base64)"

let header = {
  "Authorization": $b,
  "Content-Type": "application/json",
}

let payload = {  
  email: "testing@gmail.com"
  username: "testingUPDATED"

}


let response = (
  try {
    http patch $"($url)/user/update" ($payload | to json ) --headers $header
  } catch {|err|
    print $"FAILED UPDATE: ($err)"
    $err
  }

)

