import axios from 'axios'

const REST_API_BASE_URL = "http://localhost:8081/api/employees"
export const listEmployees = () =>{
  return axios.get(REST_API_BASE_URL);
}

export const getEmployee = (id) =>{
  return axios.get(REST_API_BASE_URL+'/'+id);
}

export const addEmployee = (employee) =>{
  return axios.post(REST_API_BASE_URL,employee)
}

export const updateEmployee = (empId, employee) =>{
  return axios.put(REST_API_BASE_URL+'/'+empId,employee)
}

export const deleteEmployee = (employeeId) =>{
  return axios.delete(REST_API_BASE_URL+'/'+employeeId)
}