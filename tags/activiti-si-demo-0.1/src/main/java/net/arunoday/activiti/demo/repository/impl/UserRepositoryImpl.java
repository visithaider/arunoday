/**
 *
 *
 */

package net.arunoday.activiti.demo.repository.impl;

import net.arunoday.activiti.demo.entity.User;
import net.arunoday.activiti.demo.repository.UserRepository;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Aparna Chaudhary
 */
@Repository("userRepository")
public class UserRepositoryImpl extends AbstractReadWriteRepository<User>
		implements UserRepository {

	protected UserRepositoryImpl() {
		super(User.class);
	}

	@Override
	public User findByUserIdPassword(String username, String password) {
		return (User) getSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.add(Restrictions.eq("password", password)).uniqueResult();
	}

	@Override
	public User findByUserId(String username) {
		return (User) getSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username)).uniqueResult();
	}
}
