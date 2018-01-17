<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>

/*
Global
*/

html {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell,
    'Open Sans', 'Helvetica Neue', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

nav {
  height: 100%;
  width: 25%;
  position: fixed;
  z-index: 1;
  top: 0;
  background-color: #111;
  overflow-x: hidden;
  padding-top: 60px;
}

nav a {
  padding: 15px 15px 15px 32px;
  text-decoration: none;
  font-size: 14px;
  color: #818181;
  display: block;
  transition: 0.3s;
  text-transform: uppercase;
  font-weight: bold;
}

nav a:hover {
  color: #f1f1f1;
  text-decoration: none;
  background-color: #4c4c36;
}

nav .searchbar {
  position: absolute;
  left: 10%; top: 20px;
  margin: auto;
  padding: 5px;
  border-radius: 5px;
  background-color: #4c4c4c;
  color: white;
  border: 0px solid black;
}

main {
  width: 75%;
  position: absolute;
  right: 0px;
  height: 100%;
}

main form {
  margin: auto;
  position: absolute;
  top: 0; left: 0; bottom: 0; right: 0;
  width: 450px;
  height: 480px;
}

main form .caption {
  font-size: 30px;
  font-weight: bold;
}

main form input[type=text]{
  font-size: 20px;
  width: 100%;
  height: 30px;
  margin-bottom: 15px;
  clear: left;
  float: left;
  display: block;
}

main form label {
  margin: 0px;
  float: left;
  display: block;
}

main h1 {
  margin: 20px;
}

fieldset {
	padding-left: 0;
	margin: 0;
	display: block;
	float: left;
}

input[type=submit] {
  margin: 20px;
  background-color: #84845e;
  border: none;
  color: white;
  padding: 10px 24px;
  border-radius: 8px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}

/* The Modal (background) */
.modal {
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
    background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
}


/*
Test.vue
*/

.front {
  -webkit-touch-callout: none;
  -webkit-user-select: none;
  -khtml-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  margin: auto;
  position: absolute;
  top: 0; left: 0; bottom: 0; right: 0;
  width: 480px;
  height: 480px;
  text-align: center;
  font-weight: bold;
}


.front .title {
  font-size: 50px;
}

.front hr {
  border-top: 2px solid #2c3e50;
  width: 60%;
}

.front .subtitle {
  color: #707a8c;
  margin-top: 15px;
  font-size: 30px;

}

/*
Verwaltung.vue
*/

table {
    border-collapse: collapse;
    margin-top: 20px;
    margin-left: auto;
    margin-right: auto;
    width: 80%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}


</style>
