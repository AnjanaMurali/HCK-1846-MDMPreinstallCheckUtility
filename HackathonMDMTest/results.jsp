<%-- <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pre-Installation Check Results</title>
</head>
<body>
    <h1>Pre-Installation Check Results</h1>

    <c:choose>
        <c:when test="${not empty error}">
            <p>Error: ${error}</p>
        </c:when>
        <c:otherwise>
            <ul>
                <li>Disk Space Check: <c:out value="${diskSpaceCheck ? 'Passed' : 'Failed'}"/> (Free disk space: <c:out value="${availableDiskSpace}"/> GB)</li>
                <li>Memory Check: <c:out value="${memoryCheck ? 'Passed' : 'Failed'}"/> (Available memory: <c:out value="${availableMemory}"/> MB)</li>
                <li>Java Installation Check: <c:out value="${javaCheck ? 'Passed' : 'Failed'}"/> (Current Java version: <c:out value="${currentJavaVersion}"/>)</li>
                <li>OS Check: <c:out value="${osCheck ? 'Passed' : 'Failed'}"/> (Current OS: <c:out value="${currentOS}"/>)</li>
                <li>Directory Access Check: <c:out value="${directoryAccessCheck ? 'Passed' : 'Failed'}"/></li>
                <li>Environment Variables Check: <c:out value="${envVarCheck ? 'Passed' : 'Failed'}"/></li>
                <li>Database Connection Check: <c:out value="${dbCheck}"/></li>
            </ul>
            <p><c:out value="${allChecksPassed ? 'All checks passed. Environment is compatible.' : 'Pre-installation checks failed. Please address the issues above.'}"/></p>
        </c:otherwise>
    </c:choose>

    <a href="index.jsp">Back to Home</a>
</body>
</html>
 --%>
 
  <%@ taglib uri="jakarta.tags.core" prefix="c" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="d" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pre-Installation Check Results</title>
    <style>
        body {
            background-image: url('images/infa-social.png'); /* Replace with your actual image path */
            background-size: cover; /* Ensure the background image covers the entire body */
            background-repeat: no-repeat; /* Prevent background image from repeating */
            font-family: Arial, sans-serif; /* Optional: Adjust font-family */
            color: #333; /* Optional: Adjust text color */
            margin: 0; /* Optional: Remove default margin */
            padding: 20px; /* Optional: Add padding */
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background-color: rgba(255, 255, 255, 0.8); /* Optional: Semi-transparent background */
            padding: 20px;
            border-radius: 5px; /* Optional: Rounded corners */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Optional: Box shadow for container */
        }

        h1 {
            text-align: center;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        ul li {
            margin-bottom: 10px;
        }

        p {
            margin-top: 20px;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007bff; /* Optional: Link color */
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Pre-Installation Check Results</h1>

        <c:choose>
            <c:when test="${not empty error}">
                <p>Error: ${error}</p>
            </c:when>
            <c:otherwise>
                <ul>
                    <li>Disk Space Check: <c:out value="${diskSpaceCheck ? 'Passed' : 'Failed'}"/> (Free disk space: <c:out value="${availableDiskSpace}"/> GB)</li>
                    <li>Memory Check: <c:out value="${memoryCheck ? 'Passed' : 'Failed'}"/> (Available memory: <c:out value="${availableMemory}"/> MB)</li>
                    <li>Java Installation Check: <c:out value="${javaCheck ? 'Passed' : 'Failed'}"/> (Current Java version: <c:out value="${currentJavaVersion}"/>)</li>
                    <li>OS Check: <c:out value="${osCheck ? 'Passed' : 'Failed'}"/> (Current OS: <c:out value="${currentOS}"/>)</li>
                    <li>Directory Access Check: <c:out value="${directoryAccessCheck ? 'Passed' : 'Failed'}"/></li>
                    <li>Environment Variables Check: <c:out value="${envVarCheck ? 'Passed' : 'Failed'}"/></li>
                    <li>Database Connection Check: <c:out value="${dbCheck}"/></li>
                </ul>
                <p><c:out value="${allChecksPassed ? 'All checks passed. Environment is compatible.' : 'Pre-installation checks failed. Please address the issues above.'}"/></p>
            </c:otherwise>
        </c:choose>

        <a href="index.jsp">Back to Home</a>
    </div>
</body>
</html>
  