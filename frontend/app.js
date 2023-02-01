const budgetResult = document.getElementsByClassName("budget-result");
const submitButton = document.querySelector("#submit");
const income = document.querySelector("#income");
const expenses = document.querySelector("#expenses");
const leftOver = document.querySelector("#left-over");

budgetResult[0].style.display = "none";

submitButton.addEventListener("click", function (event) {
  event.preventDefault();
  budgetResult[0].style.display = "block";

  const monthlyIncome = document.querySelector("#monthlyIncome").value;
  const housing = document.querySelector("#housing").value;
  const householdBills = document.querySelector("#household-bills").value;
  const transportation = document.querySelector("#transportation").value;
  const leisure = document.querySelector("#leisure").value;
  const holidays = document.querySelector("#holidays").value;
  const clothes = document.querySelector("#clothes").value;
  const savings = document.querySelector("#savings").value;

  // POST
  fetch("http://localhost:8085/api/budget/planner", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      income: monthlyIncome,
      housing: housing,
      householdBills: householdBills,
      transportation: transportation,
      leisure: leisure,
      holidays: holidays,
      clothes: clothes,
      savings: savings,
    }),
  })
    .then((response) => response.json())
    .then((data) => {
      income.textContent = "£ " + data.income.monthlyIncome;
      expenses.textContent = "£ " + data.totalExpense;
      leftOver.textContent = "£ " + data.leftOver;
    })
    .catch((error) => {
      console.error(error);
    });

  //GET // Here we dont require this since our post method returns the value.
  //   fetch("http://localhost:8085/api/budget/planner/1")
  //     .then((response) => response.json())
  //     .then((data) => {
  //       console.log(data);
  //       income.textContent = "£ " + data.income.monthlyIncome;
  //       expenses.textContent = "£ " + data.totalExpense;
  //       leftOver.textContent = "£ " + data.leftOver;
  //     })
  //     .catch((error) => {
  //       console.error(error);
  //     });

  //GET // All data
  //   fetch("http://localhost:8085/api/budget/planner")
  //     .then((response) => response.json())
  //     .then((data) => {
  //       console.log(data);
  //     })
  //     .catch((error) => {
  //       console.error(error);
  //     });
});
