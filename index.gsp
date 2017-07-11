<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>

</head>
<body>
    <content tag="nav">
    </content>

    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="kungfu_banner.png" class="grails-logo"/>
        </div>
    </div>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Welcome to Kungfu School Applicaiton</h1>

            <p>
                Congratulations, you have successfully started your first KungfuSchool application! At the moment
                this is the default page. please login first to use 
            </p>

            <div id="controllers" role="navigation">
                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each>
                </ul>
            </div>
        </section>
    </div>

</body>
</html>
