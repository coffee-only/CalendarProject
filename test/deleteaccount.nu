#! usr/bin/nu
  # env confs
  let config = open env/env.toml
  
  let url = $config.api.base_url
  let basic = $config.api.base_auth;



  let b = $"Basic ($basic | encode base64)"
  let headers = {
    "Authorization": $b,
    "Content-Type": "application/json"
  }

  let response = (
    try {
      http delete $"($url)/user/delete/1" --headers $headers
    } catch {|err|
      print $"DELETE FAILED: ($err)"
      $err
    }
  )

  print $response
