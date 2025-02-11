const express = require("express");
const fs = require("fs");
const app = express();
const dbFile = "db.json";

app.use(express.json());

// Helper function to read database file
const readDB = () => {
  try {
    const data = fs.readFileSync(dbFile, "utf8");
    return JSON.parse(data);
  } catch (err) {
    return [];
  }
};

// Helper function to write database file
const writeDB = (data) => {
  fs.writeFileSync(dbFile, JSON.stringify(data, null, 2), "utf8");
};

// Get all users
app.get("/users", (req, res) => {
  const users = readDB();
  res.json(users);
});

// Get a user by ID
app.get("/users/:id", (req, res) => {
  const users = readDB();
  const user = users.find((u) => u.id === parseInt(req.params.id));
  if (user) {
    res.json(user);
  } else {
    res.status(404).json({ message: "User not found" });
  }
});

// Add a new user
app.post("/users", (req, res) => {
  let users = readDB();
  const { name, age, email } = req.body;

  if (users.find((u) => u.name === name)) {
    return res.status(400).json({ message: "User already exists" });
  }

  const newUser = { id: users.length + 1, name, age, email };
  users.push(newUser);
  writeDB(users);

  res.status(201).json(newUser);
});

// Update user
app.put("/users/:id", (req, res) => {
  let users = readDB();
  const userIndex = users.findIndex((u) => u.id === parseInt(req.params.id));

  if (userIndex !== -1) {
    users[userIndex] = { ...users[userIndex], ...req.body };
    writeDB(users);
    res.json(users[userIndex]);
  } else {
    res.status(404).json({ message: "User not found" });
  }
});

const PORT = 3000;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
