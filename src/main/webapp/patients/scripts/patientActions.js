const modalState = {
    patient_id: -1
}

const prepareDelete = (patient_id) => {
    const modal = document.getElementById("modal");

    modalState.patient_id = patient_id;

    modal.classList.add("open");
}
const cancelDelete = () => {
    const modal = document.getElementById("modal");
    modalState.patient_id = -1;
    modal.classList.remove("open");
}

const deletePatient = (additionalPath) => {
    fetch(`${additionalPath}/patientDetails?id=${modalState.patient_id}`, {
        method: 'DELETE',
    }).then(() => {
        location.reload()
    })
}