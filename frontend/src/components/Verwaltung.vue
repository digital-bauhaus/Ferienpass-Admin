<template>
	<div>
    <h1>Veranstaltungen verwalten</h1>
    <br/>
    <input type="text" name="label" placeholder="Suchen"> <br/>
    <br/>
    <form v-if="formDataLoaded" class="form">
      <br/>
      <table>

        <tr v-for="entry in formData.sections[1].components[0].params.components">
          <th v-on:click="unfold()">{{ entry.params.label }}
          </th>
          <th> {{ entry.params.date }}</th>
          <th> {{ entry.params.org }}</th>
          <th><button v-on:click="kill($event)">löschen</button>
          <button>bearbeiten</button></th>


          <br/>

        </tr>
      </table>
    </form>

    <footer>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </footer>

	</div>
</template>

<script>
export default {
  name: 'Verwaltung',
  data () {
    return {
      formDataLoaded: false,
      formData: null
    };
  },
  created () {
    this.fetchData();
  },
  methods: {
    fetchData () {
      fetch('/static/form-data.json')
        .then(response => {
          return response.json();
        })
        .then(json => {
          this.formData = json;
          this.formDataLoaded = true;
        });
    },
    kill (event) {
      event.target.parentElement.parentElement.remove();
    }
  },
  unfold () {
  }

}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
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

