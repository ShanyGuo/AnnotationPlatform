<template>
  <div class="login-page">
    <h1 class="title">Welcome to Participate in the Word Complexity Rating Project!</h1>
    <el-form :model="form" :rules="rules" ref="form" label-width="140px" class="form-container">
      <el-form-item label="Name" prop="name">
        <el-input v-model="form.name" placeholder="Your full legal name"></el-input>
      </el-form-item>
      <el-form-item label="Email" prop="email">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="Stream" prop="stream">
        <el-select v-model="form.stream" placeholder="Select">
          <el-option
            v-for="item in streamList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Knowledge Level" prop="knowledgeLevel">
        <el-select v-model="form.knowledgeLevel" placeholder="Select">
          <el-option
              v-for="item in knowledgeList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="FPS ID" prop="fpsId">
        <el-input v-model="form.fpsId" placeholder="Enter and check carefully, as this will be used for salary payment."></el-input>
      </el-form-item>
      <el-form-item>
        <el-button level="primary" @click="submitForm">Submit</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'WelcomePage',
  data() {
    return {
      form: {
        name: '',
        email: '',
        stream: '',
        knowledgeLevel: '',
        fpsId: ''
      },
      streamList: [
        { value: 'cantonese', label: 'Cantonese' },
        { value: 'mandarin', label: 'Mandarin' }
      ],
      knowledgeList: [
        { value: 'beginner', label: 'Beginner' },
        { value: 'intermediate', label: 'Intermediate' },
        { value: 'professional', label: 'Professional' },
        { value: 'native', label: 'Native' }
      ],
      rules: {
        name: [
          { required: true, message: 'Please input your name', trigger: 'blur' }
        ],
        email: [
          { required: true, message: 'Please input your email', trigger: 'blur' },
          { type: 'email', message: 'Please input a valid email address', trigger: ['blur', 'change'] }
        ],
        stream: [
          { required: true, message: 'Please select one stream', trigger: 'blur' }
        ],
        knowledgeLevel: [
          { required: true, message: 'Please select a knowledge level', trigger: 'change' }
        ],
        fpsId: [
          { required: true, message: 'Please input your FPS ID', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    async submitForm() {
      this.$refs.form.validate(async (valid) => {
        if (valid) {
          try {
            console.log(this.form);
            this.$router.push('/rating-page');
          } catch (error) {
            console.error('There was an error!', error);
          }
        } else {
          console.log('Form validation failed');
          return false;
        }
      });
    }
  }
};
</script>

<style scoped>

.title {
  text-align: left;
  width: 40%; /* Adjust width as needed */
  margin-bottom: 20px;
  line-height: 2.0;
  font-size: 30px;
}

.login-page {
  height: 100vh; /* Full viewport height */
  display: flex;
  justify-content: center;
  align-items: center;
}

.form-container {
  width: 600px; /* Adjust width as needed */
  padding: 20px;
  background-color: #ffffff; /* White background for form */
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1); /* Optional: Add a shadow */
}

.form-container h1 {
  text-align: center;
  margin-bottom: 20px;
}
</style>
