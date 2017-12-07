<template>
  <div>
    <h1>Teilnehmer</h1>
    <br/>
    <form action="#">
      <p>
        <input type="search" placeholder="Suchen" list="Teilnehmer">
        <datalist id="Teilnehmer">
          <option value="Koenig,Thorsten"></option>
          <option value="Kohler, Marie"></option>
          <option value="Keller, Florian"></option>
          <option value="Wirtz, Felix"></option>
          <option value="Steinbrecher, Jonas"></option>
          <option value="Durr, Tina"></option>
        </datalist>
        <button>finden</button>
      </p>
    </form>
    <form v-if="formDataLoaded" class="form">
      <br/>
      <table>
        <!--<li>{{ formData.sections[2].title }}</li>-->
        <tr v-for="entry in formData.sections[2].components[0].params.components">
          <th>{{ entry.params.lastname }},

            {{ entry.params.firstname }}
          </th>
          <th>{{ entry.params.date }}</th>
          <th>{{ entry.params.street }}</th>
          <th>{{ entry.params.location }}</th>
          <th>{{ entry.params.phone }}</th>

          <th><button v-on:click="kill($event)">stornieren</button>
          <button>bearbeiten</button>
            <button>als PDF exportieren</button></th>
        </tr>
      </table>
    </form>

    <p>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </p>

  </div>
</template>

<script>

export default {
  name: 'Teilnehmer',
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
      event.target.parentElement.remove();
    }
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
</style>
