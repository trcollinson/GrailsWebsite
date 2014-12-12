<h2>MINE!</h2>
<div id="login">
	<div class="inner">
		<div class="fheader">Please Login</div>
		<form action="/Website/j_spring_security_check" method="POST" id="loginForm" class="cssform" autocomplete="off">
			<p>
				<label for="username">Username:</label>
				<input type="text" class="text_" name="j_username" id="username">
			</p>

			<p>
				<label for="password">Password:</label>
				<input type="password" class="text_" name="j_password" id="password">
			</p>

			<p id="remember_me_holder">
				<input type="checkbox" class="chk" name="_spring_security_remember_me" id="remember_me">
				<label for="remember_me">Remember me</label>
			</p>

			<p>
				<input type="submit" id="submit" value="Login">
			</p>
		</form>
	</div>
</div>