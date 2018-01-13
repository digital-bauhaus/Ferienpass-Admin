<template>
  <div>
    <h2>{{title}}</h2>
    <br/>
    <form>
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
      title: 'Admin Login'
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

input[type=text] {
    width: 15%;
    padding: 12px 18px;
    margin: 8px 0;
    box-sizing: border-box;
}

input[type=password] {
    width: 15%;
    padding: 12px 18px;
    margin: 8px 0;
    box-sizing: border-box;
}

button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 10px 24px;
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

