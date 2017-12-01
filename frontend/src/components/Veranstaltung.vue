<template>
  <div>
    <form>
      <b>Event hinzufügen: </b><br/>
      <input type="text" name="label" placeholder="Event-Name"> <br/>
      <input type="text" name="date" placeholder="Zeitraum"><br/>
      <input type="text" name="org" placeholder="Veranstalter"><br/>
      <input type="submit" value="Hinzufügen">
    </form>
    


  </div>
</template>


<script>

export default {
  name: 'Veranstaltung-erstellen',

  data () {
    return {
      title: 'Veranstaltung-erstellen'
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

