<!doctype html>
<html>
    <head>
        <title>Login</title>
        <met charset="utf-8">
            <style type="text/css">
body
    {
        width: 100%;
        background: #GGG;   
    }
.header h1
{
    font-size: 55px;
    text-shadow: 0px 0px 5px green;
}
.login
{
    width: 60%;
    height: 400px;
    background: #e3e3e3;
    margin-top: 20px;
    margin-left: 100px;
    border-radius: 20px;
    box-shadow: 0px 0px 10px green;
    font-family: serif;
    /*line-height: 400px;*/
}

.c1{

    margin-left: 180px;
    margin-top: 40px;
}
button{
    margin-right: 350px;
}

a{
    text-decoration: none;
}
</style>
</head>

<body>
		<div class="login">
		<h1>Calendar++</h1>
          <div class="c1">
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            <table><tr align="right">
		    Username  <input type="text" size=30 name="username"><br>
		    Password  <input type="password" size=30 name ="password"><br>
		    </tr>
		    </table>
		    <br>
		   	<button style="height: 25px; width: 100px" onclick="window.location.href='success.jsp'">Login</button>
		   	<br><br>
		   	<button style="height: 25px; width: 100px" onclick="window.location.href='signup.jsp'">Register</button>
          </div>
        </div>
    </body>
</html>