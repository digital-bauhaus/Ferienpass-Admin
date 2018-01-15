<template>
	<div>
    <h1>Veranstaltungen verwalten</h1>
    <br/>
    <input type="text" name="label" placeholder="Suchen"> <br/>
    <br/>
    <form v-if="formDataLoaded" class="form">
      <br/>
        <table id='myTable'>
        <tr>
          <th v-on:click="sortTable(0)">Veranstaltung</th>
          <th v-on:click="sortTable(1)">Datum</th>
          <th>Veranstalter</th>
          <th> belegt / gesamt Plätze</th>
          <th>Bearbeiten</th>

          <br/>
        </tr>
          <tr v-for="entry in formData.sections[1].components[0].params.components">
            <td v-on:click="teil($event)">{{ entry.params.label }}</td>
            <td>{{ entry.params.date }}</td>
            <td>{{ entry.params.org }}</td>
            <td>{{ entry.params.full }} / </nobr> {{ entry.params.space }}</td>
            <td><nobr><button v-on:click="kill($event)">löschen</button>
              <button>bearbeiten</button>
              <button>PDF exportieren</button></nobr>
            </td>
          </tr>
      </table>
      <!--
      <table id='myTable'>
        <tr>
          <th v-on:click="sortTable(0)">Name</th>
          <th v-on:click="sortTable(1)">Country</th>
        </tr>
        <tr>
          <td>Berglunds snabbkop</td>
          <td>Schweden</td>
        </tr>
        <tr>
          <td>North/South</td>
          <td>UK</td>
        </tr>
        <tr>
          <td>Alfreds Futterkiste</td>
          <td>Germany</td>
        </tr>
        <tr>
          <td>Koniglich Essen</td>
          <td>Germany</td>
        </tr>
        <tr>
          <td>Magazzini Alimentari</td>
          <td>Italy</td>
        </tr>
      </table>
      <table class="tbl-general tbl-orders">
        <thead>
          <tr>
            <th class="c1 first-col activesort" onclick="sortByDate()">
              <a href="#">Date</a>
            </th>
            <th class="c2">
              <a href="#">Order Number</a>
            </th>
            <th class="c3">
              <a href="#">Type</a>
            </th>
            <th class="c4 isPriceCell">
              <a href="#">Order Total</a>
            </th>
            <th class="c5">
              <a href="#">Status</a>
            </th>
            <th class="c6">
              <a href="#">Reorder</a>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="date_elements_class">07-12-2014</td>
            <td>
              <a title="view order" class="actLnk" href=""/pcam/account/orders/5475003998">5475003998</a>
            </td>
            <td>ONLINE2</td>
            <td class="isPriceCell">
              <span$50.75</span>
            </td>
            <td>Waiting for Credit Approval</td>
            <td class="isButtonCell">
              <a class="button single" href="ezroh/5475003998">Reorder Items</a>
            </td>
          </tr>
          <tr>
            <td class="date_elements_class">06-12-2014</td>
            <td>
              <a title="view order" class="actLnk" href="/pcam/account/orders/5475003998">5475003998</a>
            </td>
            <td>ONLINE </td>
            <td class="isPriceCell">
              <span>$50.75</span>
            </td>
            <td>Waiting for Credit Approval </td>
            <td class="isButtonCell">
              <a class="button single" href="ezroh/5475003998">Reorder Items</a>
            </td>
          </tr>
          <tr>
            <td class="date_elements_class">08-12-2014</td>
            <td>
              <a title="view order" class="actLnk" href="/pcam/account/orders/5475003998">5475003998</a>
            </td>
            <td>ONLINE1 </td>
            <td class="isPriceCell">
              <span>$50.75</span>
            </td>
            <td>Waiting for Credit Approval </td>
            <td class="isButtonCell">
              <a class="button single" href="ezroh/5475003998">Reorder Items</a>
            </td>
          </tr>
        </tbody>

      </table>-->
    </form>

    <footer>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </footer>

	</div>
</template>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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
      fetch('/static/form-data.json')
        .then(response => {
          return response.json();
        })
        .then(json => {
          this.dummy = json;
          this.dummyLoaded = true;
        });
    },
    sortTable (n) {
      var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount;
      switchcount = 0;
      table = document.getElementById('myTable');
      switching = true;
      dir = 'asc';
      while (switching) {
        switching = false;
        rows = table.getElementsByTagName('TR');

        for (i = 1; i < (rows.length - 1); i++) {
          shouldSwitch = false;
          x = rows[i].getElementsByTagName('TD')[n];
          y = rows[i + 1].getElementsByTagName('TD')[n];

          if (dir === 'asc') {
            if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
              shouldSwitch = true;
              break;
            }
          } else if (dir === 'desc') {
            if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
              shouldSwitch = true;
              break;
            }
          }
        }
        if (shouldSwitch) {
          rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
          switching = true;
          switchcount++;
        } else {
          if (switchcount === 0 && dir === 'asc') {
            dir = 'desc';
            switching = true;
          }
        }
      }
    },
    /* sortByDate () {
      var sortOrder = 'desc';
      var dateArray = [];
      var dateMap = {};
      var dateElements = $jQuery('.date_elements_class');

      $jQuery(dateElements).each(function() {
        dateMap[$(this).text().replace(/^\s+|\s+$/g, '')] = $(this).parent();
        dateArray.push($(this).text().replace(/^\s+|$/g, ''));
      });

      dateArray.sort();
      dateArray.sort(function(a, b) {
        a = new Date(a);
        b = new Date(b);
        return a>b;
      });

      if(sortOrder === 'desc') {
        sortOrder = 'asc';
      } else {
        dateArray.reverse();
        sortOrder = 'desc';
      }

      jQuery(jQuery("table.tbl-general").find("tbody")[0]).html("");

      for (var i = 0; i < dateArray.length; i++) {
        jQuery(jQuery("table.tbl-general").find("tbody")[0]).append(dateMap[dateArray[i]]);
      }
    }, */
    kill (event) {
      event.target.parentElement.parentElement.parentElement.remove();
    },
    teil (event) {
      event.target.parentElement.insertAdjacentHTML('afterend', '</table> <table><tr><th>Thorsten Koenig</th><th><button onclick="this.parentElement.parentElement.remove()">stornieren</button><th><button>als PDF exportieren</button></th></tr> </table>');
      event.target.parentElement.insertAdjacentHTML('afterend', '</table> <table><tr><th>Marie Kohler</th><th><button onclick="this.parentElement.parentElement.remove()">stornieren</button><th><button>als PDF exportieren</button></th></tr> </table>');
      event.target.parentElement.insertAdjacentHTML('afterend', '</table> <table><tr><th>Florian Keller</th><th><button onclick="this.parentElement.parentElement.remove()">stornieren</button><th><button>als PDF exportieren</button></th></tr> </table>');
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

