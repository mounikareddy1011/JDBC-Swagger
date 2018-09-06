
package com.entity;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class UserController {
	/*
	 * @Autowired UserServiceImpl usi;
	 */
	/*
	 * StudentService ss;
	 * 
	 * @RequestMapping( value="/",method=RequestMethod.GET,
	 * produces=MediaType.APPLICATION_JSON_VALUE) public Student meth1() { Student
	 * s=ss.meth1(); ss.addStudent(s); return s;
	 * 
	 * }
	 * 
	 * @RequestMapping( value="/",method=RequestMethod.POST,
	 * consumes=MediaType.APPLICATION_JSON_VALUE) public void
	 * addStudent(@RequestBody Student s) { ss.addStudent(s); }
	 * 
	 * @RequestMapping( value="/",method=RequestMethod.GET,
	 * produces=MediaType.APPLICATION_JSON_VALUE) public List<Student> getList() {
	 * List<Student> li=ss.getList(); return li;
	 * 
	 * }
	 */
	/*
	 * @RequestMapping(value = "/user/", method = RequestMethod.GET) public
	 * ResponseEntity<List<User>> findAllUsers() {
	 * 
	 * List<User> list = usi.findAllUsers();
	 * 
	 * if (list.isEmpty()) { return new
	 * ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You many decide to
	 * return // HttpStatus.NOT_FOUND } return new ResponseEntity<List<User>>(list,
	 * HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<User>
	 * findById(@PathVariable("id") int id) { User user = usi.findById(id); if (user
	 * == null) { System.out.println("User with id " + id + " not found"); return
	 * new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * 
	 * return new ResponseEntity<User>(user, HttpStatus.OK);
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = "/user/{username}", method = RequestMethod.GET,
	 * produces = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<User>
	 * findByName(@PathVariable("username") String username) { User user =
	 * usi.findByName(username); if (user == null) {
	 * System.out.println("User with username " + username + " not found"); return
	 * new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * 
	 * return new ResponseEntity<User>(user, HttpStatus.OK); }
	 * 
	 * 
	 * @RequestMapping(value = "/user/", method = RequestMethod.POST) public
	 * ResponseEntity<Void> saveUser(@RequestBody User us, UriComponentsBuilder
	 * ucBuilder) { if (usi.isUserExists(us)) {
	 * System.out.println("A user with username " + us.getUsername() +
	 * " already exists"); return new ResponseEntity<Void>(HttpStatus.CONFLICT); }
	 * usi.saveUser(us);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(us.getId()).
	 * toUri()); return new ResponseEntity<Void>(headers, HttpStatus.CREATED); }
	 * 
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT) public
	 * ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User
	 * user) { System.out.println("Updating User " + id);
	 * 
	 * User currentUser = usi.findById(id);
	 * 
	 * if (currentUser == null) { System.out.println("User with id " + id +
	 * " not found"); return new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * 
	 * currentUser.setUsername(user.getUsername());
	 * currentUser.setAddress(user.getAddress());
	 * currentUser.setEmail(user.getEmail());
	 * 
	 * usi.updateUser(currentUser); return new ResponseEntity<User>(currentUser,
	 * HttpStatus.OK); }
	 * 
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE) public
	 * ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
	 * System.out.println("Fetching & Deleting User with id " + id);
	 * usi.deleteUserById(id); User user = usi.findById(id); if (user == null) {
	 * System.out.println("Unable to delete. User with id " + id + " not found");
	 * return new ResponseEntity<User>(HttpStatus.NOT_FOUND); } return new
	 * ResponseEntity<User>(HttpStatus.NO_CONTENT); }
	 * 
	 * @RequestMapping(value = "/user/", method = RequestMethod.DELETE) public
	 * ResponseEntity<Void> deleteAllUsers() { usi.deleteAllUsers();
	 * System.out.println("List of users Deleted");
	 * 
	 * return new ResponseEntity<Void>(HttpStatus.OK);
	 * 
	 * }
	 */
	@Autowired
	UserDaoImpl userDAO;

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAllUsers() throws IOException {

		List<User> listUser = userDAO.findAllUsers();

		if (listUser.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);// You many decide to return
																			// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(listUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<Void> saveUser(@RequestBody User us, UriComponentsBuilder ucBuilder) {

		userDAO.saveUser(us);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(us.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		userDAO.updateUser(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
		System.out.println("Fetching & Deleting User with id " + id);
		userDAO.deleteUserById(id);

		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findById(@PathVariable("id") int id) {
		User user = userDAO.findById(id);

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}
}
