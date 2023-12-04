#include <iostream>
#include <vector>
#include <algorithm>

class Xform {
  public:
    Xform() { }
    virtual ~Xform() { }
    virtual void op(std::string& s) = 0;
};

// =======================================================

class Reverse : public Xform {
  public:
    Reverse() : Xform() { }
    virtual ~Reverse() { }
    void op(std::string& s) override {
        std::reverse(s.begin(), s.end());
    }
};

class Chars_only : public Xform {
  public:
    Chars_only() : Xform() { }
    virtual ~Chars_only() { }
    void op(std::string& s) override {
        for(int i=s.size()-1; i>=0; --i) if(!(isalpha(s[i]) || isspace(s[i]))) s.erase(i, 1);
    }
};

class Rotate : public Xform {
  public:
    Rotate(int chars = 1) : Xform(), _chars{chars} { }
    virtual ~Rotate() { }
    void op(std::string& s) override {
        s = std::string{s.begin()+_chars, s.end()} + std::string{s.begin(), s.end()-(s.size()-_chars)};
    }
  private:
    int _chars;
};

class Rtrim : public Xform {
  public:
    Rtrim(int chars = 1) : Xform(), _chars{chars} { }
    virtual ~Rtrim() { }
    void op(std::string& s) override {
        s.erase(s.size()-_chars-1);
    }
  private:
    int _chars;
};

class Ltrim : public Xform {
  public:
    Ltrim(int chars = 1) : Xform(), _chars{chars} { }
    virtual ~Ltrim() { }
    void op(std::string& s) override {
        s.erase(0, _chars);
    }
  private:
    int _chars;
};

// =======================================================

class Append : public Xform {
  public:
    Append(std::string str = "+") : Xform(), _str{str} { }
    virtual ~Append() { }
    void op(std::string& s) override {
        s.append(_str);
    }
  private:
    std::string _str;
};

class Prepend : public Xform {
  public:
    Prepend(std::string str = "+") : Xform(), _str{str} { }
    virtual ~Prepend() { }
    void op(std::string& s) override {
        s = _str + s;
    }
  private:
    std::string _str;
};

class To_upper : public Xform {
  public:
    To_upper() : Xform() { }
    virtual ~To_upper() { }
    void op(std::string& s) override {
        for(char& c : s) c = toupper(c);
    }
};

class To_lower : public Xform {
  public:
    To_lower() : Xform() { }
    virtual ~To_lower() { }
    void op(std::string& s) override {
        for(char& c : s) c = tolower(c);
    }
};

class To_mixed : public Xform {
  public:
    To_mixed() : Xform() { }
    virtual ~To_mixed() { }
    void op(std::string& s) override {
        for(int i=0; i<s.size(); ++i) if(i == 0 || s[i-1] == ' ') s[i] = toupper(s[i]);
    }
};

// =======================================================

int main() {
    std::vector<Xform*> xforms = {
        new To_lower,
        new To_mixed,
        new Ltrim{2},
        new Rtrim{2},
        new Chars_only,
        new Prepend{"@1"},
        new Append{"::"},
        new Reverse, 
        new Rotate{3},
    };

    std::cout << "Enter string to Xform: " << std::endl;
    std::string s;
    std::getline(std::cin, s);
    for(auto* f : xforms) {f->op(s);}
    std::cout << s  << std::endl;
}
