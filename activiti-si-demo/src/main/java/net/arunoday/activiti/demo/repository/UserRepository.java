/**
 *
 *
 */

package net.arunoday.activiti.demo.repository;

import net.arunoday.activiti.demo.entity.User;

/**
 * 
 * @author Aparna Chaudhary
 */
public interface UserRepository extends ReadWriteRepository<User> {

    User findByUserIdPassword(String username, String password);

    User findByUserId(String username);

}
