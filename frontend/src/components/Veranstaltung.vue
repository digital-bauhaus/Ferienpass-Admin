<template>
  <div>
    <h1> {{title}} </h1>
    <br/>
    <form>
      <b>Veranstaltung hinzufügen: </b><br/><br/>
      <input type="text" name="label" placeholder="Veranstaltungs-Name"> <br/>
      &nbsp <br/>
      <input type="text" name="date" placeholder="Zeitraum"><br/>
      &nbsp <br/>
      <input type="text" name="org" placeholder="Veranstalter"><br/>
      &nbsp <br/>
      <input type="text" name="num" placeholder="Plätze"><br/>
      &nbsp <br/>
      <input type="submit" v-on:click="create()" value="Hinzufügen">
    </form>
    <br/>


    <footer>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </footer>
  </div>

</template>


<script>

export default {
  name: 'Veranstaltung-erstellen',
  data () {
    return {
      title: 'Veranstaltung erstellen'
    }
  },
  methods: {
    create () {
      alert('Sie haben das Event erstellt');
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

footer {
    clear: both;

    background: White;
    padding: 0;
    text-align: center;
    vertical-align: middle;
    line-height: normal;
    margin: 0;
    position: fixed;
    bottom: 0px;
    width: 100%;
}
</style>

