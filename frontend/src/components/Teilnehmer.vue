<template>
  <div>
    <h1>Teilnehmer</h1>
    <br/>
    <form v-if="formDataLoaded" class="form">
      <br/>
      <ul>
        <!--<li>{{ formData.sections[2].title }}</li>-->
        <li v-for="entry in formData.sections[2].components[0].params.components">
          <h3>{{ entry.params.lastname }},

            {{ entry.params.firstname }}
          </h3>
          <p>
            {{ entry.params.date }}
            <br/>
            {{ entry.params.street }}
            <br/>
            {{ entry.params.location }}
            <br/>
            {{ entry.params.phone }}
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
