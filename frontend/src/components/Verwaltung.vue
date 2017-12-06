<template>
	<div>
    <h1>Veranstaltungen verwalten</h1>
    <form v-if="formDataLoaded" class="form">
      <br/>
      <ul>

        <li v-for="entry in formData.sections[1].components[0].params.components">
          <h3>{{ entry.params.label }}</h3>
          <p>
            {{ entry.params.date }}
            <br/>
            {{ entry.params.org }}
            <br/>
            <button v-on:click="kill($event)">löschen</button>
          </p>

          <br/>

        </li>
      </ul>
    </form>

    <p>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </p>

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
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>

