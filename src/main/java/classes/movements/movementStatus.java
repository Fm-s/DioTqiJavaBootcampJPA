package classes.movements;

public enum movementStatus {
PEDING, COMPLETED, CANCELLED, RETURNED, DENIED
}

// PENDING -> COMPLETED -> RETURNED
// PENDING -> CANCELLED
// DENIED