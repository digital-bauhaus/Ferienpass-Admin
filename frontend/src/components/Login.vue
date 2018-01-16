<template>
  <div>
    <h2>{{title}}</h2>
    <br/>
    <form id="login">
      <input type="text" name="Admin-Name" value="" placeholder="Admin"> <br/>
      <input type="password" name="Passwort" value="" placeholder="Passwort"><br/>
      <button onclick="window.location.href='/#/Test'">einloggen</button>
    </form>
  </div>
</template>


<script>

export default {
  name: 'Login',

  data () {
    return {
      title: 'Ferienpass Weimar: Administration'
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

#login {
  margin: auto;
  position: absolute;
  top: 0; left: 0; bottom: 0; right: 0;
  width: 320px;
  height: 320px;
}


input[type=text] {
    padding: 10px 12px;
    margin: 4px 0;
    box-sizing: border-box;
}

input[type=password] {
    padding: 10px 12px;
    margin: 4px 0;
    box-sizing: border-box;
}

button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 10px 24px;
    margin-top: 10px;
    border-radius: 8px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
}

a {
  color: #42b983;
}
</style>

