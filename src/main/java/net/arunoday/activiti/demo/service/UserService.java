/**
 *
 *
 */

package net.arunoday.activiti.demo.service;

import net.arunoday.activiti.demo.entity.User;

/**
 * 
 * @author Aparna Chaudhary
 */
public interface UserService {

	boolean authenticateUser(String username, String password);

	User findUser(String username);

}
