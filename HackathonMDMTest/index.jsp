<!DOCTYPE html>
<html>
<head>
    <title>MDM Pre-Installation Utility</title>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }
        body {
            background-image: url('images/infa.png');
            background-size: cover; /* Ensures the image covers the entire background */
            background-position: center; /* Centers the image */
            background-repeat: no-repeat; /* Prevents the image from repeating */
            background-attachment: fixed; /* Ensures the background image stays fixed while scrolling */
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start; /* Aligns content to the top */
        }
        h1 {
            color: white;
            margin: 100px 0;
        }
        
        form {
            background: rgba(255, 255, 255, 0.8); /* Adds a white background with opacity */
            padding: 20px;
            margin: 10px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }
        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            padding: 10px 15px;
            cursor: pointer;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        /* Tooltip container */
        .tooltip {
            position: relative;
            display: inline-block;
            width: 100%;
        }

        /* Tooltip text */
        .tooltip .tooltiptext {
            visibility: hidden;
            width: 200px;
            background-color: #555;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px 0;
            position: absolute;
            z-index: 1;
            bottom: 125%;
            left: 50%;
            margin-left: -100px;
            opacity: 0;
            transition: opacity 0.3s;
            font-family: sans-serif;
            font-size: 10px;
            font-style: italic;
        }

        /* Show the tooltip text when hovering over the tooltip container */
        .tooltip:hover .tooltiptext {
            visibility: visible;
            opacity: 1;
        }
    </style>
</head>
<body>
    <h1>MDM Pre-Installation Utility</h1>
     <form action="ConfigServlet" method="post">
        Enter MDM version: <input type="text" name="userInput" placeholder="E.g., 10.5"><br><br>
        Enter OS: <input type="text" name="userOS" placeholder="E.g., linux">
        <input type="submit" value="Submit">
    </form><br><br>
    <form action="PreInstallCheckServlet" method="post">
        <input type="submit" value="Run Pre-Installation Checks">
    </form>
               
    <form action="VulnerabilityCheck" method="post">
        <div class="tooltip">
            Enter the MDM Version: <input type="text" name="mdmVersion" placeholder="E.g., 10.5">
            <span class="tooltiptext">Please enter versions in the format 10.5</span>
        </div><br>
        <input type="submit" value="Vulnerability Info">
    </form>
</body>
</html>
