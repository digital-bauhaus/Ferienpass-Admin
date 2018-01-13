<template>
  <div>
    <h1>{{title}}</h1>
    <br/>
    <h3>Menü:</h3> <br/>
    <p>
      <a href="/#/Veranstaltung/" >Veranstaltung erstellen</a>
    </p>
    <p>
      <a href="/#/Verwaltung/" >Veranstaltungen verwalten</a>
    </p>
    <p>
      <a href="/#/Teilnehmer/" >Teilnehmer</a>
    </p>
    <p>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </p>

    <br/>
    <br/>
	</div>
</template>


<script>

export default {
  name: 'Test',

  data () {
    return {
      title: 'Admin Oberfläche'
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
  color: #022051;
  text-decoration: none;
  padding: 8px 20px;
  margin: 10px 10px;
  font-size: 200%;
  font-weight: bold;
}

a:hover {
  color: #42b983;
  text-decoration: none;
}
</style>

