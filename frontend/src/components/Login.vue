<template>
  <div>
    <h2>{{title}}</h2>
    <br/>
    <form>
      <input type="text" name="Admin-Name" value="" placeholder="Admin"> <br/>
      <input type="text" name="Passwort" value="" placeholder="Passwort"><br/>
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

a {
  color: #42b983;
}
</style>

