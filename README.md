# Multithreaded Subset Sum Solver

A high-performance Java implementation of the Subset Sum problem utilizing a recursive multithreaded algorithm with advanced thread synchronization and efficient data structures.

---

## Table of Contents
1. [Algorithmic Design & Thread Synchronization](#1-algorithmic-design--thread-synchronization)
2. [Data Structure Refactoring](#2-data-structure-refactoring)
3. [Code Documentation (Javadoc)](#3-code-documentation-javadoc)
4. [Authors & Git Collaboration Roles](#4-authors--git-collaboration-roles)
5. [Licensing](#5-licensing)

---

## 1. Algorithmic Design & Thread Synchronization

The project solves the classic **Subset Sum Problem**—determining if there is a subset of a given set of integers that sums up to a specific target value. 

### Recursive Multithreading Strategy
* **Divide and Conquer:** The search space is divided recursively. At each choice point, the algorithm branches into two paths:
  1. *Including* the current element in the running subset sum.
  2. *Excluding* the current element from the running subset sum.
* **Dynamic Thread Spawning:** To prevent thread overhead explosion while maximizing CPU utilization, threads are spawned dynamically up to a configured threshold depth or available core limit. Beyond this threshold, the algorithm falls back to sequential recursive execution.
* **Synchronization & Early Termination:** 
  * Uses atomic variables (e.g., `AtomicBoolean` or shared volatile flags) to signal all active threads when a valid subset is discovered.
  * Once a solution is found, other threads short-circuit and terminate early, significantly reducing unnecessary computations.
  * Thread-safe result collection mechanisms ensure that once a valid subset path is verified, it is safely committed without race conditions.

---

## 2. Data Structure Refactoring

The underlying data structures were refactored from primitive arrays (`int[]`) and legacy collections to `ArrayList<Integer>`:

* **Dynamic Resizing & Flexibility:** `ArrayList<Integer>` provides built-in dynamic sizing, which simplifies the addition and removal of elements during the backtracking/recursive steps without manual array management.
* **Type Safety & API Integration:** Leveraging Java Generics (`Integer`) allows seamless integration with standard Java collections and stream APIs for debugging and output formatting.
* **Memory vs. Performance Trade-offs:** While boxing/unboxing overhead of `Integer` objects was considered, the clarity, maintainability, and safety benefits outweighed raw primitive performance, mitigated by passing sublists or indices where appropriate.

---

## 3. Code Documentation (Javadoc)

All classes, methods, and fields are extensively documented using standard **Javadoc** conventions:
* Detailed descriptions of parameters (`@param`), return values (`@return`), and thrown exceptions (`@throws`).
* Thread-safety guarantees and concurrency behavior explicitly stated in class and method headers.
* Example usage included in major algorithmic entry points.

You can generate the Javadoc locally via Maven or Gradle (depending on your build tool):
```bash
mvn javadoc:javadoc
```

---

## 4. Authors & Git Collaboration Roles

This project was developed collaboratively using Git for version control and workflow management.

* **[Author 1 Name]** – *Lead Algorithm Architect & Concurrency Specialist*
  * Designed the recursive multithreading structure and thread synchronization model.
  * Implemented early-termination mechanisms and atomic state management.
* **[Author 2 Name]** – *Data Structures & Integration Engineer*
  * Refactored core logic to utilize `ArrayList<Integer>`.
  * Handled unit testing, performance benchmarking, and comprehensive Javadoc documentation.

### Git Workflow
* Feature-branch workflow (`feature/multithreading`, `refactor/arraylist-datastructure`).
* Pull Requests (PRs) subjected to code reviews and integration testing before merging into `main`.

---

## 5. Licensing

This project is licensed under the **Braude College Academic License**. 

```text
Braude College Academic License
Copyright (c) 2024-2025 [Author Names / Braude Academic College]

Permission is hereby granted, free of charge, to any student or faculty member 
of Braude Academic College obtaining a copy of this software and associated 
documentation files (the "Software"), for academic, educational, and research 
purposes, subject to the following conditions:

The above copyright notice and this permission notice shall be included in 
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR 
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```