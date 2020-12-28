<!DOCTYPE html>
<html>
<head>
  <title></title>
	 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/openUser">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/"> <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/profil">Profil</a>
      </li>
	  <li class="nav-item">
        <a class="nav-link" href="/logOut">LogOut</a>
      </li>
    </ul>
  </div>
</nav>
  <h1>${title}</h1>
  
	<form method="POST" action="/addNewElement">
	
		<div class="form-group">
			<label for="exampleInputEmail1">Name of your Element</label>
			<input type="text" class="form-control" id="nameElement"  name="name" placeholder="Enter name">
		</div>

		<div class="form-group">
			<label for="exampleTextarea">Description</label>
			<textarea class="form-control" id="exampleTextarea" rows="3" type="text" name="description" ></textarea>
		</div>
		
		<button name="idList" class="btn btn-danger" type="submit" value="${idList}" >Add a new Element</button>  
	</form>
  
	
</body>
</html>
