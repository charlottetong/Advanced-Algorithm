<html>
<head>
    <meta content="admin" name="layout"/>
    <style type="text/css">
        h2 {
            margin-top:30px;
        }
    </style>
</head>

<body>

<h1>Admin Area</h1>
<div class="row">
    <div class="span6">

        <h2>Users</h2>
        <g:form url="[controller:'user', action:'search']">
           <input name="q"></input>
           <button class="btn btn-primary">Search</button>
        </g:form>

    </div>
    <div class="span6">
        <h2>Pending Approvals</h2>

    </div>
</div>
<div class="row">
    <div class="span6">
        <h2>Recent Wiki Updates</h2>

    </div>
</div>
</body>
</html>
