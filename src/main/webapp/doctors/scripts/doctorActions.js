const modalState = {
    doctor_id: -1
}

const prepareDelete = (doctor_id) => {
    const modal = document.getElementById("modal");

    modalState.doctor_id = doctor_id;

    modal.classList.add("open");
}
const cancelDelete = () => {
    const modal = document.getElementById("modal");
    modalState.doctor_id = -1;
    modal.classList.remove("open");
}

const deletePatient = (additionalPath) => {
    fetch(`${additionalPath}/doctorDetails?id=${modalState.doctor_id}`, {
        method: 'DELETE',
    }).then(() => {
        location.reload()
    })
}