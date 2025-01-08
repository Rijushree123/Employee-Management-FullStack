import React from 'react'
import { useState, useEffect } from 'react'
import { addEmployee, getEmployee, updateEmployee } from '../services/EmployeeService'
import { useNavigate, useParams } from 'react-router-dom'
import { listDepartments } from '../services/DepartmentService'

const AddEmployeeComponent = () => {
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [department, setDepartment] = useState('')
    const [departmentId, setDepartmentId] = useState('')
    const [departments, setDepartments] = useState([])
    const [error, setError] = useState('')
    const navigate = useNavigate()
    const {id} = useParams()

    useEffect(() => {
        listDepartments().then((response)=>{
            setDepartments(response.data)
        }).catch((error) => console.error(error))
    },[])

    useEffect(() => {
      if(id){
        getEmployee(id).then((response)=>{
            setFirstName(response.data.firstName)
            setLastName(response.data.lastName)
            setEmail(response.data.email)
        })
        .catch((error) => console.error(error))
      }
    }, [id])
    
    const saveEmployee = (e) => {
        e.preventDefault()
        if (validForm()) {
            const employee = { firstName, lastName, email, departmentId }
            console.log(employee)
            addEmployee(employee)
                .then((response) => {
                    console.log(response.data)
                    navigate("/employees")
                })
                .catch((error) => console.error(error))
        }
    }
    const editEmployee = (e) =>{
        e.preventDefault()
        if (validForm()) {
            const employee = { firstName, lastName, email, departmentId }
            console.log(employee)
            updateEmployee(id, employee)
                .then((response) => {
                    console.log(response.data)
                    navigate("/employees")
                })
                .catch((error) => console.error(error))
        }
    }
    const validForm = () => {
        let valid = true
        const errorsCopy = {}
        if (firstName.trim()) {
            errorsCopy.firstName = ''
        } else {
            errorsCopy.firstName = "First Name is required"
            valid = false
        }
        if (lastName.trim()) {
            errorsCopy.lastName = ''
        } else {
            errorsCopy.lastName = "Last Name is required"
            valid = false
        }

        if (email.trim()) {
            errorsCopy.email = ''
        } else {
            errorsCopy.email = "Email is required"
            valid = false
        }
        
        if (departmentId) {
            errorsCopy.department = ''
        } else {
            errorsCopy.department = "Select Department"
            valid = false
        }
        setError(errorsCopy)
        console.log("Valid:"+valid)
        return valid
    }

    const pageTitle = () =>{
        if(id){
            return <h2 className='text-center'>Edit Employee</h2>
        }
        else{
            return <h2 className='text-center'>Add Employee</h2>
        }
    }
    return (
        <div className='container my-5'>
            <div className='row'>
                <div className='card col-md-6 offset-md-3 offset-md-3'>
                    {pageTitle()}
                    <div className='card-body'>
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>First Name: </label>
                                <input type='text'
                                    placeholder='Enter First Name'
                                    name='firstName'
                                    value={firstName}
                                    className={`form-control ${error.firstName?'is-invalid':''}`}
                                    onChange={(e) => setFirstName(e.target.value)}>
                                </input>
                                {error.firstName && <div className='invalid-feedback'>{error.firstName}</div>}
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Last Name: </label>
                                <input type='text'
                                    placeholder='Enter Last Name'
                                    name='lastName'
                                    value={lastName}
                                    className={`form-control ${error.lastName?'is-invalid':''}`}
                                    onChange={(e) => setLastName(e.target.value)}>
                                </input>
                                {error.lastName && <div className='invalid-feedback'>{error.lastName}</div>}
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Email: </label>
                                <input type='email'
                                    placeholder='Enter Email Id'
                                    name='email'
                                    value={email}
                                    className={`form-control ${error.email?'is-invalid':''}`}
                                    onChange={(e) => setEmail(e.target.value)}>
                                </input>
                                {error.email && <div className='invalid-feedback'>{error.email}</div>}
                            </div>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Department: </label>
                                <select className={`form-control ${error.department?'is-invalid':''}`}
                                        value={departmentId} 
                                        onChange={(e)=>setDepartmentId(e.target.value)}>
                                <option value="Select Department">Select Department</option>
                                {
                                     departments.map(department => 
                                        <option key={department.id} value={department.id}>
                                          {department.departmentName}
                                        </option>
                                      )
                                }
                                </select>
                                {error.department && <div className='invalid-feedback'>{error.department}</div>}
                            </div>
                            <button className='btn btn-success' onClick={(id)?editEmployee:saveEmployee}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddEmployeeComponent