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
  
	<form method="POST" action="/openForm">
		<button name="idList" class="btn btn-danger" type="submit" value="${idList}" >Add a new Element in ${title}</button>
	</form>
	
<div class="table-responsive-sm">
	<table class="table">
	<tr>
		<th>Name</th>
		<th>Description</th>
		<th>Creation Date</th>
		<th>Update Date</th>

	</tr>	
	<#list systems as system>
		<tr>
			<td>${system.getName()}</td>
			<td>${system.getDescription()}</td>
			<td>${system.getCreationDate()}</td>
			<td>${system.getUpdateDate()}</td>
			<td>
				<form method="POST" action="/deleteElement">
					<button name="id" class="btn btn-danger" type="submit" value="${system.getIdElement()}" >Delete </button>
				</form>
			</td>
			<td>
				<form method="POST" action="/openFormUpdateElement">
					<button name="id" class="btn btn-danger" type="submit" value="${system.getIdElement()}" >Update </button>
				</form>
			</td>
		</tr>
		
	</#list>
		
  </table>

</div>

 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>
