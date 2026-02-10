package api.services

import api.models.UserModel
import api.repositories.UserRepository
import org.apache.tomcat.util.net.openssl.ciphers.Encryption

class UserService(repo: UserRepository) {

    private val _repo: UserRepository = repo



    fun findAllByEmail(email: String): Iterable<UserModel>{

        return _repo.findAllByEmailIgnoreCase(email);
    }

}