package api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ConfigurationProperties(prefix="rsa")
data class RSAKeyProperties(
    val publickey: RSAPublicKey,
    val privatekey: RSAPrivateKey,
)