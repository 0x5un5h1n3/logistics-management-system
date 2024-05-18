document
  .getElementById("cargoForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();

    // Client-side validation before submitting the form
    let description = document.getElementById("description").value;
    let weight = document.getElementById("weight").value;

    if (!description || !weight) {
      alert("Please fill out all required fields.");
      return;
    }

    // Submit the form via AJAX or handle form submission
    fetch("createCargo", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        description: description,
        weight: weight,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        // Handle successful response
        console.log(data);
        alert("Cargo created successfully!");
      })
      .catch((error) => {
        // Handle error
        console.error("Error:", error);
        alert("An error occurred while creating the cargo.");
      });
  });
