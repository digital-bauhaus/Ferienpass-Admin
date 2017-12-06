<template>
  <div>
    <h1> {{title}} </h1>
    <br/>
    <form>
      <b>Event hinzufügen: </b><br/>
      <input type="text" name="label" placeholder="Event-Name"> <br/>
      &nbsp <br/>
      <input type="text" name="date" placeholder="Zeitraum"><br/>
      &nbsp <br/>
      <input type="text" name="org" placeholder="Veranstalter"><br/>
      &nbsp <br/>
      <input type="submit" value="Hinzufügen">
    </form>
    <p>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </p>


  </div>
</template>


<script>

export default {
  name: 'Veranstaltung-erstellen',
  data () {
    return {
      title: 'Veranstaltung erstellen'
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

