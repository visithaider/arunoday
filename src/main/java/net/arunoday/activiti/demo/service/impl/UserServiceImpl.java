/**
 *
 *
 */

package net.arunoday.activiti.demo.service.impl;

import net.arunoday.activiti.demo.entity.User;
import net.arunoday.activiti.demo.repository.UserRepository;
import net.arunoday.activiti.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * @author Aparna Chaudhary
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUserIdPassword(username, password);
        return user != null ? true : false;
    }

    @Override
    public User findUser(String username) {
        return userRepository.findByUserId(username);
    }


}
