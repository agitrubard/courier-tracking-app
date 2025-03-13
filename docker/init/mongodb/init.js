db = db.getSiblingDB('courier_tracking');
db.createUser({
  user: "courier_tracking",
  pwd: "courier_tracking_pass",
  roles: [
    {
      role: "readWrite",
      db: "courier_tracking"
    }
  ]
});
