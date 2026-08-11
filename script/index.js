const form = document.getElementById("login-form");
const messageDiv = document.getElementById("message-container");

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    try {
        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        if (response.ok) {
            setTimeout(() => {
                messageDiv.textContent = "Logueado con exito!";
                messageDiv.style.color = "green";
            }, 3000)
            location.href = "src/pages/home/index.html";
        } else {
            messageDiv.textContent = "Error en las credenciales!";
            messageDiv.style.color = "red";
        }
    } catch (error) {
        console.error(`Error de conexion: ${error}`);
        messageDiv.textContent = "Error de conexion con el Backend";
        messageDiv.style.color = "red";
        messageDiv.style.backgroundColor = "#FFB5B5"
    }
});