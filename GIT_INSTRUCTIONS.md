# Git Instructions for Selenium Automation Framework (सरल भाषा में Git गाइड)

यह document आपको step-by-step बताएगा कि अपने Selenium Automation Framework को कैसे Git repository में push करें।

## 🚀 Code Push करने के Basic Steps (बिल्कुल नए users के लिए)

अगर आप पहली बार Git use कर रहे हैं, तो इन simple steps को follow करें:

### Step 1: Git Install करें (अगर पहले से installed नहीं है)

1. **Windows के लिए**: 
   - [Git for Windows](https://git-scm.com/download/win) से download करें और install करें
   - Installation के दौरान default options accept करें

2. **Installation verify करें**:
   - Command Prompt (CMD) या PowerShell open करें
   - Type करें: `git --version`
   - Output मिलना चाहिए: `git version 2.xx.x`

### Step 2: Git Configuration Set करें

Command Prompt या PowerShell open करें और निम्न commands run करें:

```
git config --global user.name "Aapka Naam"
git config --global user.email "aapka.email@example.com"
```

### Step 3: GitHub Account Create करें (अगर पहले से नहीं है)

1. [GitHub](https://github.com/) पर जाएँ
2. Sign Up button click करें
3. Username, email और password provide करके account create करें

### Step 4: GitHub पर New Repository Create करें

1. GitHub पर login करें
2. Top-right corner में "+" icon click करें, फिर "New repository" select करें
3. Repository का name दें (उदाहरण: "selenium-automation-framework")
4. Description add करें (optional)
5. Repository को Public या Private set करें
6. "Create repository" button click करें

### Step 5: Local Git Repository Initialize करें

1. Command Prompt (CMD) या PowerShell open करें
2. Apne project folder में navigate करें:
   ```
   cd path\to\SeleniumAutomationFramework
   ```
3. Git repository initialize करें:
   ```
   git init
   ```

### Step 6: Files को Staging Area में Add करें

Staging area में सभी files add करने के लिए:
```
git add .
```

या specific files add करने के लिए:
```
git add README.md
git add src/test/java/com/qasmarts/tests/LoginTest.java
```

### Step 7: Changes को Commit करें

```
git commit -m "Initial commit: Selenium Automation Framework with POM structure"
```

### Step 8: Remote Repository को Add करें

```
git remote add origin https://github.com/yourusername/selenium-automation-framework.git
```
(URL को अपने GitHub repository URL से replace करें)

### Step 9: Code को GitHub पर Push करें

```
git push -u origin master
```
या main branch के लिए:
```
git push -u origin main
```

### Step 10: Login Credentials Enter करें

- Push command के बाद GitHub username और password enter करने के लिए prompt मिलेगा
- Personal Access Token (PAT) use करने की recommendation मिलेगी (मॉडर्न security के लिए)

## 📱 Personal Access Token (PAT) Create और Use करना

GitHub अब password based authentication support नहीं करता, इसलिए PAT create करें:

1. GitHub पर login करें
2. Top-right corner में अपनी profile photo click करें
3. Settings select करें
4. Left sidebar में "Developer settings" click करें
5. "Personal access tokens" > "Tokens (classic)" select करें
6. "Generate new token" click करें
7. Token को name दें (e.g., "Selenium Framework Access")
8. Expiration select करें
9. Select permissions (minimum: `repo`)
10. "Generate token" button click करें
11. Generated token को copy करें और safe place में save करें (यह token आपके password की जगह use होगा)

Push करते समय password के बजाय इस token को use करें।

## 🔄 Daily Workflow - Code Push करना (Regular Users के लिए)

अगर आपने पहले ही repository setup कर लिया है और regularly changes push करते हैं:

### 1. Latest Changes Pull करें

```
git pull origin main
```

### 2. Changes Verify करें

```
git status
```

### 3. Changes Add करें

```
git add .
```

### 4. Changes Commit करें

```
git commit -m "Added new test cases for iFrame functionality"
```

### 5. Changes Push करें

```
git push origin main
```

## 🌿 Branch के साथ काम करना

New feature या bug fix के लिए branch create करना best practice है:

### Branch Create करना
```
git checkout -b feature/iframe-tests
```

### Branch में Changes Add और Commit करना
```
git add .
git commit -m "Added iFrame test functionality"
```

### Branch को Push करना
```
git push origin feature/iframe-tests
```

### Main Branch में Switch Back करना
```
git checkout main
```

## 🔄 Git के सबसे Common Commands

| Command | Description |
|---------|-------------|
| `git status` | Repository का status देखें |
| `git add .` | सभी changes staging area में add करें |
| `git commit -m "message"` | Changes को commit करें |
| `git push origin main` | Changes को remote repository में push करें |
| `git pull origin main` | Latest changes fetch करें |
| `git branch` | All branches list देखें |
| `git checkout branchname` | Specific branch में switch करें |
| `git log` | Commit history देखें |
| `git diff` | Unstaged changes देखें |

## ⚠️ Common Problems और Solutions

### Issue 1: "Repository not found" error
- Remote repository URL correct है verify करें
- GitHub पर permissions check करें

### Issue 2: "Failed to push some refs" error
- पहले `git pull origin main` run करें
- फिर changes resolve करें और push करें

### Issue 3: Merge conflicts
1. Conflicting files edit करें (conflict markers `<<<<<<<`, `=======`, `>>>>>>>` को resolve करें)
2. `git add .` run करें
3. `git commit -m "Resolved merge conflicts"` run करें
4. `git push origin main` run करें

## 🛑 Typical Merge Conflict का Example

Merge conflict ऐसा दिखता है:

```
<<<<<<< HEAD
// यह current branch का code है
public void testIFrameContent() {
    // Current branch implementation
}
=======
// यह incoming branch का code है
public void testIFrameContent() {
    // Incoming branch implementation  
}
>>>>>>> feature/iframe-tests
```

इसे resolve करने के लिए, conflict markers (`<<<<<<<`, `=======`, `>>>>>>>`) हटाकर final code रखें।

## 🎯 GitHub Repository की Link Setup करने के लिए

Make sure आपके पास correct repository URL है. GitHub repository page पर "Code" button click करके URL copy करें:

```
git remote add origin https://github.com/yourusername/selenium-automation-framework.git
```

## 📋 Summary: Code Push करने के Quick Steps

नए users के लिए (first time setup):
```
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/yourusername/repo-name.git
git push -u origin main
```

Regular users के लिए (daily updates):
```
git pull origin main
git add .
git commit -m "Your commit message"
git push origin main
```

## 🎓 Additional Resources

- [Git Cheat Sheet (PDF)](https://education.github.com/git-cheat-sheet-education.pdf)
- [Interactive Git Learning](https://learngitbranching.js.org/)
- [Visual Git Guide](https://marklodato.github.io/visual-git-guide/index-en.html) 