<template>
  <div class="container">
    <div class="instruction-area">
      <h2>{{stream}} Words Complexity Rating</h2>
      <p>Instructions for using the rating system:</p>
      <ul>
        <li>Rate each item from 1 to 5 stars. 1 star means very easy to understand the keyword in the given sentence, 5 stars means hard to understand the keyword in the given sentence.</li>
        <li>The "Keywords" column indicates the word that needs to be rated, the "Index" column shows the position of the keyword in the sentence, and the "Sentence" column provides the context sentence around the keyword.</li>
        <li>Scroll down and make sure to rate all samples before submitting.</li>
        <li>All samples MUST be rated in one sitting, which takes about 1 hour, without breaks in this process.</li>
        <li>Click the submit button to send your ratings.</li>
      </ul>
    </div>
    <div class="content-area">
      <el-table :data="items" style="width: 100%" height="775" :header-cell-style="{'background-color': '#f5f5f5'}" border>
        <el-table-column prop="keywords" label="Keywords" width="120">
        </el-table-column>
        <el-table-column prop="index" label="Index" width="120">
        </el-table-column>
        <el-table-column prop="sentence" label="Sentence" width="760">
        </el-table-column>
        <el-table-column label="Complexity Rating" width="180">
          <template slot-scope="scope">
            <el-rate v-model="scope.row.rating" :max="5" @change="handleRatingChange(scope.row)"></el-rate>
          </template>
        </el-table-column>
      </el-table>
      <div class="button-container">
        <el-button type="primary" @click="submitRatings">Submit Ratings</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { MessageBox } from 'element-ui';

export default {
  data() {
    return {
      stream: "",
      fileName: "",
      items: [],
    };
  },
  mounted() {
    this.fetchData(); // Call the fetchData method when the component is mounted
  },
  methods: {
    fetchData() {
      const cachedData = localStorage.getItem('cachedData');
      const cachedStream = localStorage.getItem('cachedStream');
      if (cachedData) {
        this.items = JSON.parse(cachedData);
        this.stream = cachedStream;
      } else {
        // Replace with your JSON file or API endpoint
        const url = 'http://localhost:8080/bucket/downloadObj';

        // Use axios to fetch data
        axios.get(url)
        .then(response => {
          let fName = response.data.fileName;
          this.fileName = fName;
          if (fName.startsWith("mandarin_")) {
            this.stream = "Mandarin";
          } else {
            this.stream = "Cantonese";
          }

          let fileContent = response.data.fileContent;
            if (typeof fileContent === 'string') {
              try {
                fileContent = JSON.parse(fileContent);
              } catch (error) {
                console.error('Error parsing JSON:', error);
                return; // Exit early if parsing fails
              }
            }
            this.items = fileContent;
            localStorage.setItem('cachedData', JSON.stringify(fileContent));
            localStorage.setItem('cachedStream', this.stream);
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
      }

    },
    submitRatings() {
      const invalidItems = this.items.filter(item => item.rating === -1);
        if (invalidItems.length > 0) {
          MessageBox.alert('All items must be rated before submitting.', 'Error', {
            confirmButtonText: 'OK',
            showClose: false,
            type: 'warning'
          });
          return;
        } else {
          const cachedUserName = localStorage.getItem('cachedUserName');
          const cachedFpsId = localStorage.getItem('cachedFpsId');
          const dto = {
            userName: cachedUserName,
            fpsId: cachedFpsId,
            fileName: this.fileName,
            fileContent: this.items,
          };
          console.log(dto);
          axios.post('http://localhost:8080/bucket/uploadObj', dto)
            .then(response => {
                if (response.status === 200) {
                  console.log("success");
                  this.$router.push('/thank-you')
                } else {
                  MessageBox.alert('User Invalid', 'Error', {
                    confirmButtonText: 'OK',
                    showClose: false,
                    type: 'warning'
                  });
                }
            })

        }
    }
  }
};
</script>

<style scoped>

.container {
  display: flex;
  height: 100vh;
}

.instruction-area {
  width: 250px;
  padding: 20px;
  background-color: #f5f5f5;
  overflow-y: auto; /* To allow scrolling if instructions are too long */
  position: fixed; /* Fixes the instruction area */
  height: 100%; /* Full height */
}

.content-area {
  margin-left: 300px; /* Same width as the instruction area + some gap */
  //padding: 20px;
  overflow-y: auto; /* Allows scrolling of the table content */
  position: fixed;
  flex-grow: 1;
}

.button-container {
  display: flex;
  justify-content: center;
  width: 100%;
}

.el-table {
  width: 100%;
}

.el-button {
  margin-top: 20px;
}

</style>
