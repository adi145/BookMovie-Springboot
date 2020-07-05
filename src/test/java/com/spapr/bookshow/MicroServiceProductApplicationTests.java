package com.spapr.bookshow;

import com.spapr.bookshow.Constants.Constant;
import com.spapr.bookshow.controller.UserController;
import com.spapr.bookshow.controller.response.GeneralResponse;
import com.spapr.bookshow.exception.UserNotFoundException;
import com.spapr.bookshow.factory.ResponseFactory;
import com.spapr.bookshow.model.User;
import com.spapr.bookshow.repository.UserRepository;
import com.spapr.bookshow.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MicroServiceProductApplicationTests {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	ResponseFactory responseFactory;

	@Test
	public void test_createUser_Success_200() throws UserNotFoundException {
		System.out.print("++++++++++++++++++++++++++++");
		User user = new User();
		user.setMobile_number("782374283");
		user.setName("Test");
		user.setEmail("Test@aaratech.com");
		user.setPassword("Testsdjsjd");
		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseObject.setMessage(Constant.USER_REGISTER_SUCCESS);
		responseObject.setData(user);
		when(responseFactory.success(any(),any())).thenReturn(ResponseEntity.ok(responseObject));
		when(userService.createUser(any())).thenReturn(user);
		ResponseEntity responseEntity = userController.createUser(user);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_getAllusers_Success_200() throws UserNotFoundException {
		System.out.print("++++++++++++++++++++++++++++");
		User user = new User();
		user.setMobile_number("782374283");
		user.setName("Test");
		user.setEmail("Test@aaratech.com");
		user.setPassword("Testsdjsjd");

		User user1 = new User();
		user1.setMobile_number("782374283");
		user1.setName("Test");
		user1.setEmail("Test@aaratech.com");
		user1.setPassword("Testsdjsjd");
		List listSource = new ArrayList();

		listSource.add(user);
		listSource.add(user1);

		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseObject.setMessage("");
		responseObject.setData(listSource);
		when(responseFactory.success(any(),any())).thenReturn(ResponseEntity.ok(responseObject));
		when(userService.getAllUser()).thenReturn(listSource);
		ResponseEntity responseEntity = userController.getAllUsers();
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_getUserById() throws UserNotFoundException {
		User user = new User();
		user.setMobile_number("782374283");
		user.setName("Test");
		user.setEmail("Test@aaratech.com");
		user.setPassword("Testsdjsjd");
		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseObject.setMessage("");
		responseObject.setData(user);
		when(responseFactory.success(any(),any())).thenReturn(ResponseEntity.ok(responseObject));
		when(userService.getUserById(1)).thenReturn(user);
		ResponseEntity responseEntity = userController.getUserById(1);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_updateUserById() throws UserNotFoundException {
		User user = new User();
		user.setMobile_number("782374283");
		user.setName("Test11");
		user.setEmail("Test11@aaratech.com");
		user.setPassword("testmethod");
		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseObject.setMessage("");
		responseObject.setData(user);
		when(responseFactory.success(any(),any())).thenReturn(ResponseEntity.ok(responseObject));
		when(userService.updateUserById(any(),any())).thenReturn(user);
		ResponseEntity responseEntity = userController.updateUser(1,user);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	public void test_deleteUserById() throws UserNotFoundException {
		User user = new User();
		user.setMobile_number("782374283");
		user.setName("Test11");
		user.setEmail("Test11@aaratech.com");
		user.setPassword("testmethod");
		GeneralResponse<Object> responseObject = new GeneralResponse();
		responseObject.setMessage("");
		responseObject.setData(user);
		when(responseFactory.success(any(),any())).thenReturn(ResponseEntity.ok(responseObject));
		when(userService.deleteUser(any())).thenReturn(user);
		ResponseEntity responseEntity = userController.deleteUser(1);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}