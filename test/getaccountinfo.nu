#! usr/bin/nu

let config = open env/env.toml

let url = $config.api.base_url
let basic_auth = $config.api.base_auth

let b = $"Basic ($basic_auth | encode base64)"

let header = {
  "Authorization": $b,
  "Content-Type": "application/json"
}

let response = (
  try {
    http get $"($url)/user/getinfo/2" --headers $header
  } catch {|err|
    print $"GET FAILED: ($err)"
    $err
  }
)

print $response
